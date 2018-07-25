package com.wangyy.erp.erp.public_.销售.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.ColumnIdentifier;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.core.util.OptionalUtil;
import com.speedment.runtime.field.ComparableField;
import com.speedment.runtime.field.IntField;
import com.speedment.runtime.field.IntForeignKeyField;
import com.speedment.runtime.field.StringField;
import com.speedment.runtime.typemapper.TypeMapper;
import com.speedment.runtime.typemapper.time.TimestampToLocalDateTimeMapper;
import com.wangyy.erp.erp.public_.会员.会员;
import com.wangyy.erp.erp.public_.销售.销售;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * The generated base for the {@link com.wangyy.erp.erp.public_.销售.销售}-interface
 * representing entities of the {@code 销售}-table in the database.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public interface Generated销售 {
    
    /**
     * This Field corresponds to the {@link 销售} field that can be obtained using
     * the {@link 销售#get编号()} method.
     */
    IntField<销售, Integer> 编号 = IntField.create(
        Identifier.编号,
        销售::get编号,
        销售::set编号,
        TypeMapper.primitive(),
        true
    );
    /**
     * This Field corresponds to the {@link 销售} field that can be obtained using
     * the {@link 销售#get单号()} method.
     */
    StringField<销售, String> 单号 = StringField.create(
        Identifier.单号,
        o -> OptionalUtil.unwrap(o.get单号()),
        销售::set单号,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link 销售} field that can be obtained using
     * the {@link 销售#get商品()} method.
     */
    StringField<销售, String> 商品 = StringField.create(
        Identifier.商品,
        销售::get商品,
        销售::set商品,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link 销售} field that can be obtained using
     * the {@link 销售#get票据()} method.
     */
    StringField<销售, String> 票据 = StringField.create(
        Identifier.票据,
        销售::get票据,
        销售::set票据,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link 销售} field that can be obtained using
     * the {@link 销售#get客户()} method.
     */
    StringField<销售, String> 客户 = StringField.create(
        Identifier.客户,
        销售::get客户,
        销售::set客户,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link 销售} field that can be obtained using
     * the {@link 销售#get日期()} method.
     */
    StringField<销售, String> 日期 = StringField.create(
        Identifier.日期,
        销售::get日期,
        销售::set日期,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link 销售} field that can be obtained using
     * the {@link 销售#get创建时间()} method.
     */
    ComparableField<销售, Timestamp, LocalDateTime> 创建时间 = ComparableField.create(
        Identifier.创建时间,
        销售::get创建时间,
        销售::set创建时间,
        new TimestampToLocalDateTimeMapper(),
        false
    );
    /**
     * This Field corresponds to the {@link 销售} field that can be obtained using
     * the {@link 销售#get会员编号()} method.
     */
    IntForeignKeyField<销售, Integer, 会员> 会员编号 = IntForeignKeyField.create(
        Identifier.会员编号,
        销售::get会员编号,
        销售::set会员编号,
        会员.会员标识,
        TypeMapper.primitive(),
        false
    );
    
    /**
     * Returns the 编号 of this 销售. The 编号 field corresponds to the database
     * column erp.public.销售.编号.
     * 
     * @return the 编号 of this 销售
     */
    int get编号();
    
    /**
     * Returns the 单号 of this 销售. The 单号 field corresponds to the database
     * column erp.public.销售.单号.
     * 
     * @return the 单号 of this 销售
     */
    Optional<String> get单号();
    
    /**
     * Returns the 商品 of this 销售. The 商品 field corresponds to the database
     * column erp.public.销售.商品.
     * 
     * @return the 商品 of this 销售
     */
    String get商品();
    
    /**
     * Returns the 票据 of this 销售. The 票据 field corresponds to the database
     * column erp.public.销售.票据.
     * 
     * @return the 票据 of this 销售
     */
    String get票据();
    
    /**
     * Returns the 客户 of this 销售. The 客户 field corresponds to the database
     * column erp.public.销售.客户.
     * 
     * @return the 客户 of this 销售
     */
    String get客户();
    
    /**
     * Returns the 日期 of this 销售. The 日期 field corresponds to the database
     * column erp.public.销售.日期.
     * 
     * @return the 日期 of this 销售
     */
    String get日期();
    
    /**
     * Returns the 创建时间 of this 销售. The 创建时间 field corresponds to the database
     * column erp.public.销售.创建时间.
     * 
     * @return the 创建时间 of this 销售
     */
    LocalDateTime get创建时间();
    
    /**
     * Returns the 会员编号 of this 销售. The 会员编号 field corresponds to the database
     * column erp.public.销售.会员编号.
     * 
     * @return the 会员编号 of this 销售
     */
    int get会员编号();
    
    /**
     * Sets the 编号 of this 销售. The 编号 field corresponds to the database column
     * erp.public.销售.编号.
     * 
     * @param 编号 to set of this 销售
     * @return   this 销售 instance
     */
    销售 set编号(int 编号);
    
    /**
     * Sets the 单号 of this 销售. The 单号 field corresponds to the database column
     * erp.public.销售.单号.
     * 
     * @param 单号 to set of this 销售
     * @return   this 销售 instance
     */
    销售 set单号(String 单号);
    
    /**
     * Sets the 商品 of this 销售. The 商品 field corresponds to the database column
     * erp.public.销售.商品.
     * 
     * @param 商品 to set of this 销售
     * @return   this 销售 instance
     */
    销售 set商品(String 商品);
    
    /**
     * Sets the 票据 of this 销售. The 票据 field corresponds to the database column
     * erp.public.销售.票据.
     * 
     * @param 票据 to set of this 销售
     * @return   this 销售 instance
     */
    销售 set票据(String 票据);
    
    /**
     * Sets the 客户 of this 销售. The 客户 field corresponds to the database column
     * erp.public.销售.客户.
     * 
     * @param 客户 to set of this 销售
     * @return   this 销售 instance
     */
    销售 set客户(String 客户);
    
    /**
     * Sets the 日期 of this 销售. The 日期 field corresponds to the database column
     * erp.public.销售.日期.
     * 
     * @param 日期 to set of this 销售
     * @return   this 销售 instance
     */
    销售 set日期(String 日期);
    
    /**
     * Sets the 创建时间 of this 销售. The 创建时间 field corresponds to the database
     * column erp.public.销售.创建时间.
     * 
     * @param 创建时间 to set of this 销售
     * @return     this 销售 instance
     */
    销售 set创建时间(LocalDateTime 创建时间);
    
    /**
     * Sets the 会员编号 of this 销售. The 会员编号 field corresponds to the database
     * column erp.public.销售.会员编号.
     * 
     * @param 会员编号 to set of this 销售
     * @return     this 销售 instance
     */
    销售 set会员编号(int 会员编号);
    
    /**
     * Queries the specified manager for the referenced 会员. If no such 会员
     * exists, an {@code NullPointerException} will be thrown.
     * 
     * @param foreignManager the manager to query for the entity
     * @return               the foreign entity referenced
     */
    会员 find会员编号(Manager<会员> foreignManager);
    
    enum Identifier implements ColumnIdentifier<销售> {
        
        编号   ("编号"),
        单号   ("单号"),
        商品   ("商品"),
        票据   ("票据"),
        客户   ("客户"),
        日期   ("日期"),
        创建时间 ("创建时间"),
        会员编号 ("会员编号");
        
        private final String columnId;
        private final TableIdentifier<销售> tableIdentifier;
        
        Identifier(String columnId) {
            this.columnId        = columnId;
            this.tableIdentifier = TableIdentifier.of(    getDbmsId(), 
                getSchemaId(), 
                getTableId());
        }
        
        @Override
        public String getDbmsId() {
            return "erp";
        }
        
        @Override
        public String getSchemaId() {
            return "public";
        }
        
        @Override
        public String getTableId() {
            return "销售";
        }
        
        @Override
        public String getColumnId() {
            return this.columnId;
        }
        
        @Override
        public TableIdentifier<销售> asTableIdentifier() {
            return this.tableIdentifier;
        }
    }
}