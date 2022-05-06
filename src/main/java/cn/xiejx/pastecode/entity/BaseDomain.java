package cn.xiejx.pastecode.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * there is introduction
 *
 * @author xjx
 * @date 2022/3/24 23:02
 */
@Data
public class BaseDomain implements Serializable {
    @Serial
    private static final long serialVersionUID = 5853206902412007289L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("create_time")
    private Long createTime;
    @TableField("modify_time")
    private Long modifyTime;

    public void fillAllTime() {
        long now = System.currentTimeMillis();
        createTime = now;
        modifyTime = now;
    }

    public void fillModifyTime() {
        modifyTime = System.currentTimeMillis();
    }
}
