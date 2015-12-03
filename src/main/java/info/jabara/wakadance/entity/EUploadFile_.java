package info.jabara.wakadance.entity;

import jabara.jpa.entity.EntityBase_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-12-03T19:53:36.731+0900")
@StaticMetamodel(EUploadFile.class)
public class EUploadFile_ extends EntityBase_ {
	public static volatile SingularAttribute<EUploadFile, String> personName;
	public static volatile SingularAttribute<EUploadFile, String> uploadFileName;
	public static volatile SingularAttribute<EUploadFile, SendState> sendState;
	public static volatile SingularAttribute<EUploadFile, String> localFilePath;
	public static volatile SingularAttribute<EUploadFile, String> contentType;
	public static volatile SingularAttribute<EUploadFile, Long> size;
}
