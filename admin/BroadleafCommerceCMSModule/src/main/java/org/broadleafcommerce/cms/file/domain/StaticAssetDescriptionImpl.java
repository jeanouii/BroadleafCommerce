/*
 * Copyright 2008-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.broadleafcommerce.cms.file.domain;

import org.broadleafcommerce.openadmin.audit.Auditable;
import org.broadleafcommerce.openadmin.audit.AuditableListener;
import org.broadleafcommerce.presentation.AdminPresentation;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created by bpolster.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BLC_STATIC_ASSET_DESCRIPTION")
@EntityListeners(value = { AuditableListener.class })
@Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blCMSElements")
public class StaticAssetDescriptionImpl implements StaticAssetDescription {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "StaticAssetDescriptionId", strategy = GenerationType.TABLE)
    @TableGenerator(name = "StaticAssetDescriptionId", table = "SEQUENCE_GENERATOR", pkColumnName = "ID_NAME", valueColumnName = "ID_VAL", pkColumnValue = "StaticAssetDescriptionId", allocationSize = 10)
    @Column(name = "STATIC_ASSET_DESC_ID")
    protected Long id;

    @Embedded
    @AdminPresentation(excluded = true)
    protected Auditable auditable = new Auditable();

    @Column (name = "DESCRIPTION")
    @AdminPresentation(friendlyName="Description")
    protected String description;

    @Column (name = "LONG_DESCRIPTION")
    @AdminPresentation(friendlyName="Long Description", largeEntry = true)
    protected String longDescription;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getLongDescription() {
        return longDescription;
    }

    @Override
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    @Override
    public StaticAssetDescription cloneEntity() {
        StaticAssetDescriptionImpl newAssetDescription = new StaticAssetDescriptionImpl();
        newAssetDescription.description = description;
        newAssetDescription.longDescription = longDescription;

        return newAssetDescription;
    }

    public Auditable getAuditable() {
        return auditable;
    }

    public void setAuditable(Auditable auditable) {
        this.auditable = auditable;
    }
}

