package info.jabara.wakadance;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Paths;
import java.util.Calendar;

import jabara.general.EnvironmentUtil;

/**
 * @author jabaraster
 */
public final class Environment {
    /**
     *
     */
    public static final String  APPLICATION_NAME                      = "WakaDance";                                                        //$NON-NLS-1$

    private static final String PARAM_PREFIX                          = APPLICATION_NAME + "_";                                             //$NON-NLS-1$

    /**
     *
     */
    public static final String  PARAM_HIBERNATE_HBM2DDL_AUTO          = PARAM_PREFIX + "hibernateHbm2ddlAuto";                              //$NON-NLS-1$
    /**
     *
     */
    public static final String  DEFAULT_HIBERNATE_HBM2DDL_AUTO        = "update";                                                           //$NON-NLS-1$
    /**
     *
     */
    public static final String  PARAM_H2_DATABASE_PATH                = PARAM_PREFIX + "h2DatabasePath";                                    //$NON-NLS-1$
    /**
     *
     */
    public static final String  PARAM_H2_DB_NAME_SUFFIX               = PARAM_PREFIX + "h2DbNameSuffix";                                    //$NON-NLS-1$
    /**
     * デフォルト値はカレントディレクトリ以下のtarget/db/<アプリケーション名>です. <br>
     * 即ち、以下のコードでパスを組み立てています. <br>
     * <code>
     * Paths.get(".").toAbsolutePath().normalize() + "/target/db/" + Environment.APPLICATION_NAME
     * </code>
     */
    public static final String  DEFAULT_H2_DATABASE_PATH              = Paths.get(".").toAbsolutePath().normalize()                         //$NON-NLS-1$
            + "/target/db/" + APPLICATION_NAME;                                                                                             //$NON-NLS-1$

    /**
     *
     */
    public static final String  PARAM_COPYRIGHT                       = PARAM_PREFIX + "copyright";                                         //$NON-NLS-1$

    /**
     *
     */
    public static final String  DEFAULT_COPYRIGHT                     = APPLICATION_NAME + " " + Calendar.getInstance().get(Calendar.YEAR); //$NON-NLS-1$

    /**
     *
     */
    public static final int     DEFAULT_S3_UPLOAD_TIMEOUT_SECONDS     = 30;

    /**
     *
     */
    public static final int     DEFAULT_S3_UPLOAD_PART_SIZE_MEGABYTES = 5;

    /**
     *
     */
    public static final String  DEFAULT_S3_BUCKET_NAME                = "waka-dance-test";                                                  //$NON-NLS-1$

    private Environment() {
        // 処理なし
    }

    /**
     * @param pType -
     * @throws IllegalAccessException -
     */
    public static void dumpConstantDeclarations(final Class<?> pType) throws IllegalAccessException {
        for (final Field field : pType.getFields()) {
            final int modifiers = field.getModifiers();
            if (!Modifier.isStatic(modifiers)) {
                continue;
            }
            if (!Modifier.isFinal(modifiers)) {
                continue;
            }
            System.out.println(field.getName() + "\t" + field.getType().getName() + "\t" + field.get(null)); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * @return アプリケーション名.
     * @see #APPLICATION_NAME
     */
    public static String getApplicationName() {
        return APPLICATION_NAME;
    }

    /**
     * @return -
     */
    public static String getAwsAccessKey() {
        return EnvironmentUtil.getStringUnsafe(PARAM_PREFIX + "awsAccessKey"); //$NON-NLS-1$
    }

    /**
     * @return -
     */
    public static String getAwsSecreKey() {
        return EnvironmentUtil.getStringUnsafe(PARAM_PREFIX + "awsSecretKey"); //$NON-NLS-1$
    }

    /**
     * @return -
     */
    public static String getCopyright() {
        return EnvironmentUtil.getString(PARAM_COPYRIGHT, DEFAULT_COPYRIGHT);
    }

    /**
     * @return データベースにH2を使う場合のデータベースファイルのパス. <br>
     *         {@link #PARAM_H2_DB_NAME_SUFFIX}の指定がある場合は、末尾に付与して返します.
     * @see #PARAM_H2_DATABASE_PATH
     * @see #PARAM_H2_DB_NAME_SUFFIX
     * @see #DEFAULT_H2_DATABASE_PATH
     */
    public static String getH2DatabasePath() {
        final String path = EnvironmentUtil.getString(PARAM_H2_DATABASE_PATH, DEFAULT_H2_DATABASE_PATH);
        final String suffix = EnvironmentUtil.getString(PARAM_H2_DB_NAME_SUFFIX, null);
        return suffix == null ? path : path + "_" + suffix; //$NON-NLS-1$
    }

    /**
     * @return -
     * @see #PARAM_HIBERNATE_HBM2DDL_AUTO
     * @see #DEFAULT_HIBERNATE_HBM2DDL_AUTO
     */
    public static String getHbm2DdlAuto() {
        return EnvironmentUtil.getString(PARAM_HIBERNATE_HBM2DDL_AUTO, DEFAULT_HIBERNATE_HBM2DDL_AUTO);
    }

    /**
     * @return -
     */
    public static String getMavenCommand() {
        return EnvironmentUtil.getString(PARAM_PREFIX + "mavenCommand", "mvn"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * @return -
     */
    public static File getMavenDirectory() {
        final String dir = EnvironmentUtil.getString(PARAM_PREFIX + "mavenDirectory", //$NON-NLS-1$
                "/Users/jabaraster/Documents/Develop/Java/workspace/projects/waka-dance-lib"); //$NON-NLS-1$
        return new File(dir);
    }

    /**
     * @return -
     */
    public static String getS3BucketName() {
        return EnvironmentUtil.getString(PARAM_PREFIX + "s3BucketName", DEFAULT_S3_BUCKET_NAME); //$NON-NLS-1$
    }

    /**
     * @return -
     */
    public static int getS3UploadPartSizeMegaBytes() {
        return EnvironmentUtil.getInt(PARAM_PREFIX + "s3UploadPartSizeMegaBytes", DEFAULT_S3_UPLOAD_PART_SIZE_MEGABYTES); //$NON-NLS-1$
    }

    /**
     * @return -
     */
    public static int getS3UploadTimeoutSeconds() {
        return EnvironmentUtil.getInt(PARAM_PREFIX + "s3UploadTimeoutSeconds", DEFAULT_S3_UPLOAD_TIMEOUT_SECONDS); //$NON-NLS-1$
    }

    /**
     * @param pArgs 起動引数.
     * @throws IllegalAccessException -
     */
    public static void main(final String[] pArgs) throws IllegalAccessException {
        dumpConstantDeclarations(Environment.class);
    }
}
