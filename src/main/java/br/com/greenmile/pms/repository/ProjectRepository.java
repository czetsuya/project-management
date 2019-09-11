package br.com.greenmile.pms.repository;

import br.com.greenmile.pms.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByEnabledFalse();

    List<Project> findAllByEnabledTrue();
}
