package cn.allcheer.lihui.dal.domain.repository;

import cn.allcheer.lihui.dal.domain.dao.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lihui 2017 12 26
 * 继承JpaRepository<T,K>，第一个泛型参数是实体对象的名称，第二个是主键类型
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser,Long> {
    /**
     * 按用户名查找用户
     * @param userName 用户名
     * @return 用户对象
     */
    SysUser findByUserName(String userName);
}
