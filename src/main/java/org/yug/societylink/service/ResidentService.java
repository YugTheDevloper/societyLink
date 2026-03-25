package org.yug.societylink.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.yug.societylink.dto.LoginDTO;
import org.yug.societylink.dto.ResidentDTO;
import org.yug.societylink.dto.UpdateDTO;

@Service
public interface ResidentService {

    public ResidentDTO registerResident(ResidentDTO residentDTO);
    public Page<ResidentDTO> getAllResidents(int pageNumber, int pageSize, String sortBy);
    public ResidentDTO loginResident(LoginDTO loginDTO);
    ResidentDTO updateResident(Long id , UpdateDTO updateDTO);
}
