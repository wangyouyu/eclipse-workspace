package com.wangyy.erp.erp.public_.用户.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.field.Field;
import com.wangyy.erp.erp.public_.用户.用户;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

/**
 * The generated base interface for the manager of every {@link
 * com.wangyy.erp.erp.public_.用户.用户} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public interface Generated用户Manager extends Manager<用户> {
    
    TableIdentifier<用户> IDENTIFIER = TableIdentifier.of("erp", "public", "用户");
    List<Field<用户>> FIELDS = unmodifiableList(asList(
        用户.账号,
        用户.密码,
        用户.会员编号,
        用户.详情
    ));
    
    @Override
    default Class<用户> getEntityClass() {
        return 用户.class;
    }
}