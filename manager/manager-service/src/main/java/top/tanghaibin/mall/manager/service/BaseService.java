package top.tanghaibin.mall.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import top.tanghaibin.mall.manager.pojo.BasePojo;

import java.util.Date;
import java.util.List;

/**
 * Created by tangh on 2017/1/1.
 */
public abstract class BaseService<T extends BasePojo> {

    @Autowired
    private Mapper<T> mapper;
    
    public T queryById(final Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<T> queryAll() {
        return mapper.selectAll();
    }

    public T queryOne(T record) {
        return mapper.selectOne(record);
    }

    public List<T> queryListByWhere(T record) {
        return mapper.select(record);
    }

    public PageInfo<T> queryPageListByWhere(Integer pageNumber, Integer pageSize, T record) {
        PageHelper.startPage(pageNumber, pageSize);
        return new PageInfo<T>(mapper.select(record));
    }

    public PageInfo<T> queryPageListByWhereAndOrderBy(Integer pageNumber, Integer pageSize, T record, String orderBy) {
        PageHelper.startPage(pageNumber, pageSize);
        Example example = new Example(record.getClass());
        example.setOrderByClause(orderBy);
        return new PageInfo<T>(mapper.selectByExample(example));
    }

    public Integer save(T record) {
        record.setCreated(new Date());
        record.setUpdated(record.getCreated());
        return mapper.insert(record);
    }

    public Integer saveBySelective(T record) {
        record.setCreated(new Date());
        record.setUpdated(record.getCreated());
        return mapper.insertSelective(record);
    }
    public Integer updateByPrimaryKey(T record) {
        record.setUpdated(new Date());
        return mapper.updateByPrimaryKey(record);
    }

    public Integer updateBySelective(T record) {
        record.setUpdated(new Date());
        return mapper.updateByPrimaryKeySelective(record);
    }

    public Integer deleteById(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }

    public Integer deleteByIds(List<Object> ids) {
        if(CollectionUtils.isEmpty(ids)) {
            return 0;
        }
        Integer result = 0;
        for(Object id : ids) {
            result += mapper.deleteByPrimaryKey(id);
        }
        return result;
    }

    public Integer deleteByWhere(T record) {
        return mapper.delete(record);
    }

    public Integer deleteByIn(final String property, List<Object> values, Class<T> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, values);
        return mapper.deleteByExample(example);
    }
}
