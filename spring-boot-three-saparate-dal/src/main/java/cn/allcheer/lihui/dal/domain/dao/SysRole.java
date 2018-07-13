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
@Setter
@Getter
@ToString
public class SysRole {
    /**
     * 角色id
     */
    @Id
    @GeneratedValue
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private List<SysResource> resources;
}
