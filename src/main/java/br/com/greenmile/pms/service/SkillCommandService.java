package br.com.greenmile.pms.service;

import br.com.greenmile.pms.common.api.ISkillCommandService;
import br.com.greenmile.pms.common.dto.SkillDTO;
import br.com.greenmile.pms.common.dto.SkillResultDTO;
import br.com.greenmile.pms.entity.Skill;
import br.com.greenmile.pms.repository.SkillRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class SkillCommandService implements ISkillCommandService {

    @Autowired
    private SkillRepository repository;

    @Override
    public SkillResultDTO save(SkillDTO dto) {
        Skill skill = new Skill();
        SkillResultDTO result = new SkillResultDTO();

        BeanUtils.copyProperties(dto, skill);
        Skill save = this.repository.save(skill);
        BeanUtils.copyProperties(save, result);
        return result;
    }

    public SkillResultDTO save(Skill skill) {
        SkillResultDTO result = new SkillResultDTO();

        Skill save = this.repository.save(skill);
        BeanUtils.copyProperties(save, result);
        return result;
    }

    @Override
    public SkillResultDTO update(Long id, SkillDTO dto) {
        Optional<Skill> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Skill skill = optional.get();
            SkillResultDTO result = new SkillResultDTO();

            BeanUtils.copyProperties(dto, skill);
            Skill update = this.repository.save(skill);
            BeanUtils.copyProperties(update, result);
            return result;
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        if (!ObjectUtils.isEmpty(id) && this.repository.existsById(id)) {
            this.repository.deleteById(id);
        }
    }
}
