package com.wangyy.erp.erp.public_.仓库.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.manager.AbstractManager;
import com.speedment.runtime.field.Field;
import com.wangyy.erp.erp.public_.仓库.仓库;
import com.wangyy.erp.erp.public_.仓库.仓库Manager;

import java.util.stream.Stream;

/**
 * The generated base implementation for the manager of every {@link
 * com.wangyy.erp.erp.public_.仓库.仓库} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class Generated仓库ManagerImpl 
extends AbstractManager<仓库> 
implements Generated仓库Manager {
    
    private final TableIdentifier<仓库> tableIdentifier;
    
    protected Generated仓库ManagerImpl() {
        this.tableIdentifier = TableIdentifier.of("erp", "public", "仓库");
    }
    
    @Override
    public TableIdentifier<仓库> getTableIdentifier() {
        return tableIdentifier;
    }
    
    @Override
    public Stream<Field<仓库>> fields() {
        return 仓库Manager.FIELDS.stream();
    }
    
    @Override
    public Stream<Field<仓库>> primaryKeyFields() {
        return Stream.of(
            仓库.仓库标识
        );
    }
}