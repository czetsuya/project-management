package br.com.greenmile.pms.service;

import br.com.greenmile.pms.common.api.IProjectCommandService;
import br.com.greenmile.pms.common.dto.ProjectDTO;
import br.com.greenmile.pms.common.dto.ProjectResultDTO;
import br.com.greenmile.pms.entity.Project;
import br.com.greenmile.pms.repository.ProjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class ProjectCommandService implements IProjectCommandService {

    @Autowired
    private ProjectRepository repository;

    @Override
    public ProjectResultDTO save(ProjectDTO dto) {
        Project project = new Project();
        ProjectResultDTO result = new ProjectResultDTO();

        BeanUtils.copyProperties(dto, project);
        Project save = this.repository.save(project);
        BeanUtils.copyProperties(save, result);
        return result;
    }

    @Override
    public ProjectResultDTO update(Long id, ProjectDTO dto) {
        Optional<Project> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Project project = optional.get();
            ProjectResultDTO result = new ProjectResultDTO();

            BeanUtils.copyProperties(dto, project);
            Project update = this.repository.save(project);
            BeanUtils.copyProperties(update, result);
            return result;
        }
        return null;
    }

    @Override
    public void archive(Long id, boolean value) {
        Optional<Project> optional = this.repository.findById(id);

        if (optional.isPresent()) {
            Project project = optional.get();
            project.setEnabled(value);
            this.repository.save(project);
        }
    }

    @Override
    public void delete(Long id) {
        if (!ObjectUtils.isEmpty(id) && this.repository.existsById(id)) {
            this.repository.deleteById(id);
        }
    }
}
