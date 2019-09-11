package br.com.greenmile.pms.common.api;

import br.com.greenmile.pms.common.dto.SkillDTO;
import br.com.greenmile.pms.common.dto.SkillResultDTO;

public interface ISkillCommandService {

    SkillResultDTO save(SkillDTO dto);

    SkillResultDTO update(Long id, SkillDTO dto);

    void delete(Long id);
}
