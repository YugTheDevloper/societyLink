    package org.yug.societylink.service;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.domain.Sort;
    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;

    import org.springframework.stereotype.Service;
    import org.yug.societylink.dto.ComplaintAdminResponseDTO;
    import org.yug.societylink.dto.ComplaintRequestDTO;
    import org.yug.societylink.dto.ComplaintResponseDTO;
    import org.yug.societylink.exception.UserNotFoundException;
    import org.yug.societylink.model.Complaint;
    import org.yug.societylink.model.Resident;
    import org.yug.societylink.repository.ComplaintRepository;
    import org.yug.societylink.repository.ResidentRepository;


    import java.util.List;

    @Service
    public class ComplaintService {

        @Autowired
        private ComplaintRepository complaintRepository;
        @Autowired
        private ResidentRepository residentRepository;
        @Autowired
        private ModelMapper modelMapper;

        public ComplaintResponseDTO saveComplaint(Long residentId, ComplaintRequestDTO complaintRequestDTO){

            Resident resident=(residentRepository.findById(residentId)).orElseThrow(()-> new UserNotFoundException("NO RESIDENT IS REGISTERED WITH THIS "+ residentId));

            Complaint updatedComplaint = modelMapper.map(complaintRequestDTO,Complaint.class);
            updatedComplaint.setResident(resident);
            Complaint complaintWithTimeStamp = complaintRepository.save(updatedComplaint);
            ComplaintResponseDTO complaintResponseDTO = modelMapper.map(complaintWithTimeStamp,ComplaintResponseDTO.class);


            return complaintResponseDTO;
        }

        public List<ComplaintResponseDTO> getComplaintByResidentId(Long id){

            if(!residentRepository.existsById(id)){
                throw new UserNotFoundException("USER WITH ID"+ id +"DOESN'T EXIST");

            }

            List<Complaint> entities = complaintRepository.findByResidentId(id);
            return entities.stream()
                    .map(entity -> modelMapper.map(entity, ComplaintResponseDTO.class)).toList();



        }

        public Page<ComplaintAdminResponseDTO> getComplaintsForAdmin(int page , int size , String sortBy){

            Pageable pageable= PageRequest.of(page,size , Sort.by(sortBy));
            Page<Complaint> entityPage=complaintRepository.findAll(pageable);
            return entityPage.map(entity-> modelMapper.map(entity,ComplaintAdminResponseDTO.class));


        }




    }
