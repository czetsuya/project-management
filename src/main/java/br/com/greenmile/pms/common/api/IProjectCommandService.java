package br.com.greenmile.pms.common.api;

import br.com.greenmile.pms.common.dto.ProjectDTO;
import br.com.greenmile.pms.common.dto.ProjectResultDTO;

public interface IProjectCommandService {

    ProjectResultDTO save(ProjectDTO dto);

    ProjectResultDTO update(Long id, ProjectDTO dto);

    void archive(Long id, boolean value);

    void delete(Long id);
}
