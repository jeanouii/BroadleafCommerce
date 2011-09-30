package org.broadleafcommerce.cms.file.domain;

import org.broadleafcommerce.openadmin.audit.Auditable;
import org.broadleafcommerce.openadmin.audit.AuditableListener;
import org.broadleafcommerce.presentation.AdminPresentation;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by jfischer
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BLC_ASSET_STORAGE")
@EntityListeners(value = { AuditableListener.class })
public class StaticAssetStorageImpl implements StaticAssetStorage {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "StaticAssetStorageId", strategy = GenerationType.TABLE)
    @TableGenerator(name = "StaticAssetStorageId", table = "SEQUENCE_GENERATOR", pkColumnName = "ID_NAME", valueColumnName = "ID_VAL", pkColumnValue = "StaticAssetStorageImpl", allocationSize = 10)
    @Column(name = "STATIC_ASSET_STORAGE_ID")
    protected Long id;

    @Embedded
    @AdminPresentation(excluded = true)
    protected Auditable auditable = new Auditable();

    @Column(name ="STATIC_ASSET_ID", nullable = false)
    protected Long staticAssetId;

    @Column (name = "FILE_DATA")
    @Lob
    protected Blob fileData;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Blob getFileData() {
        return fileData;
    }

    @Override
    public void setFileData(Blob fileData) {
        this.fileData = fileData;
    }

    @Override
    public Long getStaticAssetId() {
        return staticAssetId;
    }

    @Override
    public void setStaticAssetId(Long staticAssetId) {
        this.staticAssetId = staticAssetId;
    }

    public Auditable getAuditable() {
        return auditable;
    }

    public void setAuditable(Auditable auditable) {
        this.auditable = auditable;
    }
}
