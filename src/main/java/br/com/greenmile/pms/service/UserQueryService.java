package br.com.greenmile.pms.service;

import br.com.greenmile.pms.common.api.IUserQueryService;
import br.com.greenmile.pms.common.util.SortUtils;
import br.com.greenmile.pms.entity.User;
import br.com.greenmile.pms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UserQueryService implements IUserQueryService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return this.repository.getOne(id);
    }

    @Override
    public boolean existsById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return false;
        }
        return this.repository.existsById(id);
    }

    @Override
    public List<User> getAll() {
        Sort sort = SortUtils.getSort(Sort.Direction.ASC, "name");
        return this.repository.findAll(sort);
    }
}
