package com.shoukailiang.community.article.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.article.req.LabelREQ;
import com.shoukailiang.community.entities.Label;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-05
 */
public interface LabelMapper extends BaseMapper<Label> {
    /**
     * *	只要将非 limit 的sql 语句写在 对应的 id="queryPage"里面（LabelMapper.xml），
     * *	不需要手动去分页，而mybaits-plus会自动实现分页
     * *	但是必须第1个参数传入Page，第2个参数通过 @Param 取别名，
     * *	最终查询到的数据会被封装到IPage实现里面
     * @param page
     * @param req
     * @return
     */
    IPage<Label> queryPage(IPage<Label> page, @Param("req") LabelREQ req);


    /**
     * 热门标签
     * @return
     */
    List<Label> selectHotTag();
}
