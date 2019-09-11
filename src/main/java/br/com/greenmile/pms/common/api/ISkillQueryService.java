package br.com.greenmile.pms.common.api;

import br.com.greenmile.pms.entity.Skill;

import java.util.List;

public interface ISkillQueryService {

    Skill findById(Long id);

    boolean existsById(Long id);

    List<Skill> getAll();
}
