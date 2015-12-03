/**
 *
 */
package info.jabara.wakadance.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import info.jabara.wakadance.entity.EUploadFile;
import info.jabara.wakadance.entity.SendState;
import info.jabara.wakadance.model.UploadFileInfo;
import info.jabara.wakadance.model.UploadInfo;
import jabara.jpa.entity.EntityBase_;

/**
 * @author jabaraster
 */
@Dependent
public class UploadFileService {

    private final EntityManager em;

    /**
     * @param pEntityManager -
     */
    @Inject
    public UploadFileService(final EntityManager pEntityManager) {
        this.em = pEntityManager;
    }

    /**
     * @return -
     */
    public List<EUploadFile> getAll() {
        final CriteriaBuilder builder = this.em.getCriteriaBuilder();
        final CriteriaQuery<EUploadFile> query = builder.createQuery(EUploadFile.class);
        final Root<EUploadFile> root = query.from(EUploadFile.class);
        query.orderBy(builder.desc(root.get(EntityBase_.created)));
        return this.em.createQuery(query).getResultList();
    }

    /**
     * @param pUploadInfo -
     * @return -
     */
    @Transactional
    public UploadInfo insert(final UploadInfo pUploadInfo) {
        final List<UploadFileInfo> files = new ArrayList<>();
        for (final UploadFileInfo fileInfo : pUploadInfo.getUploadFileInfos()) {
            final EUploadFile file = new EUploadFile();
            file.setPersonName(pUploadInfo.getPersonName() == null ? "" : pUploadInfo.getPersonName()); //$NON-NLS-1$
            file.setSendState(SendState.UNUPLOAD);
            file.setLocalFilePath(fileInfo.getSaveFilePath().toFile().getAbsolutePath());
            file.setContentType(fileInfo.getContentType());
            file.setUploadFileName(fileInfo.getFileName());
            file.setSize(fileInfo.getSize());
            this.em.persist(file);

            final UploadFileInfo db = new UploadFileInfo(fileInfo.getSaveFilePath(), fileInfo.getFileName(), fileInfo.getContentType(),
                    fileInfo.getSize(), fileInfo.getDbKeyValue());
            files.add(db);
        }
        return new UploadInfo(pUploadInfo.getPersonName(), files);
    }
}
