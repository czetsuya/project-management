package br.com.greenmile.pms.common.api;

import br.com.greenmile.pms.entity.User;

import java.util.List;

public interface IUserQueryService {

    User findById(Long id);

    boolean existsById(Long id);

    List<User> getAll();
}
