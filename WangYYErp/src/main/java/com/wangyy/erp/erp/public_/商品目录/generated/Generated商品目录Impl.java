package com.wangyy.erp.erp.public_.商品目录.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.core.util.OptionalUtil;
import com.wangyy.erp.erp.public_.商品目录.商品目录;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * The generated base implementation of the {@link
 * com.wangyy.erp.erp.public_.商品目录.商品目录}-interface.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class Generated商品目录Impl implements 商品目录 {
    
    private String 商品标识码;
    private int 会员;
    private String 详情;
    private String 库存;
    
    protected Generated商品目录Impl() {}
    
    @Override
    public String get商品标识码() {
        return 商品标识码;
    }
    
    @Override
    public int get会员() {
        return 会员;
    }
    
    @Override
    public Optional<String> get详情() {
        return Optional.ofNullable(详情);
    }
    
    @Override
    public Optional<String> get库存() {
        return Optional.ofNullable(库存);
    }
    
    @Override
    public 商品目录 set商品标识码(String 商品标识码) {
        this.商品标识码 = 商品标识码;
        return this;
    }
    
    @Override
    public 商品目录 set会员(int 会员) {
        this.会员 = 会员;
        return this;
    }
    
    @Override
    public 商品目录 set详情(String 详情) {
        this.详情 = 详情;
        return this;
    }
    
    @Override
    public 商品目录 set库存(String 库存) {
        this.库存 = 库存;
        return this;
    }
    
    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner(", ", "{ ", " }");
        sj.add("商品标识码 = " + Objects.toString(get商品标识码()));
        sj.add("会员 = "    + Objects.toString(get会员()));
        sj.add("详情 = "    + Objects.toString(OptionalUtil.unwrap(get详情())));
        sj.add("库存 = "    + Objects.toString(OptionalUtil.unwrap(get库存())));
        return "商品目录Impl " + sj.toString();
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (!(that instanceof 商品目录)) { return false; }
        final 商品目录 that商品目录 = (商品目录)that;
        if (!Objects.equals(this.get商品标识码(), that商品目录.get商品标识码())) { return false; }
        if (this.get会员() != that商品目录.get会员()) { return false; }
        if (!Objects.equals(this.get详情(), that商品目录.get详情())) { return false; }
        if (!Objects.equals(this.get库存(), that商品目录.get库存())) { return false; }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(get商品标识码());
        hash = 31 * hash + Integer.hashCode(get会员());
        hash = 31 * hash + Objects.hashCode(OptionalUtil.unwrap(get详情()));
        hash = 31 * hash + Objects.hashCode(OptionalUtil.unwrap(get库存()));
        return hash;
    }
}