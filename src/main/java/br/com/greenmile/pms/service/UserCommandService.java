package br.com.greenmile.pms.service;

import br.com.greenmile.pms.common.api.IUserCommandService;
import br.com.greenmile.pms.common.dto.SkillDTO;
import br.com.greenmile.pms.common.dto.SkillResultDTO;
import br.com.greenmile.pms.common.dto.UserDTO;
import br.com.greenmile.pms.common.dto.UserResultDTO;
import br.com.greenmile.pms.entity.Skill;
import br.com.greenmile.pms.entity.User;
import br.com.greenmile.pms.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class UserCommandService implements IUserCommandService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillCommandService skillCommandService;

    @Override
    public UserResultDTO save(UserDTO dto) {
        User user = new User();
        UserResultDTO result = new UserResultDTO();

        BeanUtils.copyProperties(dto, user);
        User save = this.userRepository.save(user);
        BeanUtils.copyProperties(save, result);
        return result;
    }

    public SkillResultDTO saveSkill(Long userId, SkillDTO dto) {
        Optional<User> optional = this.userRepository.findById(userId);

        if (optional.isPresent()) {
            User user = optional.get();

            Skill skill = new Skill();
            skill.setUser(user);

            BeanUtils.copyProperties(dto, skill);
            return this.skillCommandService.save(skill);
        }
        return null;
    }

    @Override
    public UserResultDTO update(Long id, UserDTO dto) {
        Optional<User> optional = this.userRepository.findById(id);

        if (optional.isPresent()) {
            User user = optional.get();
            UserResultDTO result = new UserResultDTO();

            BeanUtils.copyProperties(dto, user);
            User update = this.userRepository.save(user);
            BeanUtils.copyProperties(update, result);
            return result;
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (!ObjectUtils.isEmpty(id) && this.userRepository.existsById(id)) {
            this.userRepository.deleteById(id);
        }
    }
}
