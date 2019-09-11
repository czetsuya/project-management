package br.com.greenmile.pms.common.api;

import br.com.greenmile.pms.common.dto.UserDTO;
import br.com.greenmile.pms.common.dto.UserResultDTO;

public interface IUserCommandService {

    UserResultDTO save(UserDTO dto);

    UserResultDTO update(Long id, UserDTO dto);

    void delete(Long id);
}
