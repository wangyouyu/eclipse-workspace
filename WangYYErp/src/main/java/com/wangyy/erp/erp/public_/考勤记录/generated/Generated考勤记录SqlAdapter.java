package com.wangyy.erp.erp.public_.考勤记录.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.common.injector.annotation.ExecuteBefore;
import com.speedment.runtime.config.Project;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.component.ProjectComponent;
import com.speedment.runtime.core.component.SqlAdapter;
import com.speedment.runtime.core.component.sql.SqlTypeMapperHelper;
import com.speedment.runtime.core.db.SqlFunction;
import com.wangyy.erp.erp.public_.考勤记录.考勤记录;
import com.wangyy.erp.erp.public_.考勤记录.考勤记录Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.speedment.common.injector.State.RESOLVED;

/**
 * The generated Sql Adapter for a {@link com.wangyy.erp.erp.public_.考勤记录.考勤记录}
 * entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class Generated考勤记录SqlAdapter implements SqlAdapter<考勤记录> {
    
    private final TableIdentifier<考勤记录> tableIdentifier;
    private SqlTypeMapperHelper<Timestamp, LocalDateTime> 日期Helper;
    
    protected Generated考勤记录SqlAdapter() {
        this.tableIdentifier = TableIdentifier.of("erp", "public", "考勤记录");
    }
    
    protected 考勤记录 apply(ResultSet resultSet, int offset) throws SQLException {
        return createEntity()
            .set日期(   日期Helper.apply(resultSet.getTimestamp(1 + offset)))
            .set用户(   resultSet.getString(2 + offset))
            .set考勤明细( resultSet.getString(3 + offset))
            .set会员编号( resultSet.getInt(4 + offset))
            ;
    }
    
    protected 考勤记录Impl createEntity() {
        return new 考勤记录Impl();
    }
    
    @Override
    public TableIdentifier<考勤记录> identifier() {
        return tableIdentifier;
    }
    
    @Override
    public SqlFunction<ResultSet, 考勤记录> entityMapper() {
        return entityMapper(0);
    }
    
    @Override
    public SqlFunction<ResultSet, 考勤记录> entityMapper(int offset) {
        return rs -> apply(rs, offset);
    }
    
    @ExecuteBefore(RESOLVED)
    void createHelpers(ProjectComponent projectComponent) {
        final Project project = projectComponent.getProject();
        日期Helper = SqlTypeMapperHelper.create(project, 考勤记录.日期, 考勤记录.class);
    }
}