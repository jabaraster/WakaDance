package info.jabara.wakadance.entity;

import jabara.jpa.entity.EntityBase_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-12-03T12:47:22.665+0900")
@StaticMetamodel(EUploadFile.class)
public class EUploadFile_ extends EntityBase_ {
	public static volatile SingularAttribute<EUploadFile, String> personName;
	public static volatile SingularAttribute<EUploadFile, UploadState> uploadState;
	public static volatile SingularAttribute<EUploadFile, String> localFilePath;
}
