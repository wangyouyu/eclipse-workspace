package com.wangyy.erp.erp.public_.考勤记录.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.manager.AbstractManager;
import com.speedment.runtime.field.Field;
import com.wangyy.erp.erp.public_.考勤记录.考勤记录;
import com.wangyy.erp.erp.public_.考勤记录.考勤记录Manager;

import java.util.stream.Stream;

/**
 * The generated base implementation for the manager of every {@link
 * com.wangyy.erp.erp.public_.考勤记录.考勤记录} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class Generated考勤记录ManagerImpl 
extends AbstractManager<考勤记录> 
implements Generated考勤记录Manager {
    
    private final TableIdentifier<考勤记录> tableIdentifier;
    
    protected Generated考勤记录ManagerImpl() {
        this.tableIdentifier = TableIdentifier.of("erp", "public", "考勤记录");
    }
    
    @Override
    public TableIdentifier<考勤记录> getTableIdentifier() {
        return tableIdentifier;
    }
    
    @Override
    public Stream<Field<考勤记录>> fields() {
        return 考勤记录Manager.FIELDS.stream();
    }
    
    @Override
    public Stream<Field<考勤记录>> primaryKeyFields() {
        return Stream.empty();
    }
}