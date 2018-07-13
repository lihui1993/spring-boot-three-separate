package cn.allcheer.lihui.dal.domain.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author lihui
 */
@Entity
@Getter
@Setter
@ToString
public class SysUser {
    @Id
    @GeneratedValue
    private Integer id;
    private String userName;
    private String passWord;
    /**
     * 配置多对多关系
     * 对应权限表
     */
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<SysRole> roles;
}
