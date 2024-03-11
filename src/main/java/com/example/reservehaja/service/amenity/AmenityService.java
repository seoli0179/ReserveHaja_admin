package com.example.reservehaja.service.amenity;

import com.example.reservehaja.data.dao.admin.AdminDAO;
import com.example.reservehaja.data.dao.amenity.AmenityDAO;
import com.example.reservehaja.data.dto.amenity.AmenityListResponseDto;
import com.example.reservehaja.data.dto.amenity.AmenityRequestDto;
import com.example.reservehaja.data.dto.amenity.AmenityResponseDto;
import com.example.reservehaja.data.dto.amenity.AmenityUpdateRequestDto;
import com.example.reservehaja.data.dto.reserveInfoJson.ReserveJsonArrayRequestDto;
import com.example.reservehaja.data.entity.Admin;
import com.example.reservehaja.data.entity.Amenity;
import com.example.reservehaja.data.entity.Round;
import com.example.reservehaja.data.entity.RoundCell;
import com.example.reservehaja.data.repo.AdminRepository;
import com.example.reservehaja.data.repo.AmenityRepository;
import com.example.reservehaja.data.state.RoundCellState;
import com.example.reservehaja.data.state.ServiceState;
import com.example.reservehaja.service.round.RoundService;
import com.example.reservehaja.service.roundCell.RoundCellService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AmenityService {

    private final AmenityDAO amenityDAO;
    private final AdminDAO adminDAO;
    private final AdminRepository adminRepository;
    private final RoundService roundService;
    private final RoundCellService roundCellService;
    private final AmenityRepository amenityRepository;

    public boolean addAmenity(AmenityRequestDto amenityRequestDto, String username) {
        Optional<Admin> admin = adminDAO.findAdmin(username);
        return admin.filter(value -> amenityDAO.addAmenity(amenityRequestDto.toEntity(value))).isPresent();
    }

    public boolean addAmenityJson(ReserveJsonArrayRequestDto requestDto) {

        return amenityDAO.addAmenity(requestDto.toEntity(0));

    }


    public int addAmenityJsonArray(ReserveJsonArrayRequestDto requestDto) {

        int count = 0;
        Optional<Admin> admin = adminRepository.findByAdminId("admin");
        LocalDate currentDate = LocalDate.now();

        for (int i = 0; i < requestDto.getRow().size(); i++) {

            Amenity amenity = requestDto.toEntity(i);
            amenity.setAdmin(admin.get());

            if (currentDate.isBefore(amenity.getRcptEndDate().toLocalDate()) && amenity.getSvcStateName() == ServiceState.RESERVE_START) {

                Optional<Amenity> amenity2 = amenityRepository.findBySvcId(amenity.getSvcId());
                if (amenity2.isPresent()) {
                    amenity = amenity2.get();
                    roundService.deleteRoundList(amenity.getId());
                } else {
                    amenityRepository.save(amenity);
                }

                Round round1 = new Round();
                round1.setAmenity(amenity);
                round1.setRoundName("09:00 ~ 12:00 오전");
                round1.setRoundUseBeginTime(LocalTime.of(9, 0));
                round1.setRoundUseEndTime(LocalTime.of(12, 0));

                Round round2 = new Round();
                round2.setAmenity(amenity);
                round2.setRoundName("12:00 ~ 18:00 오후");
                round2.setRoundUseBeginTime(LocalTime.of(13, 0));
                round2.setRoundUseEndTime(LocalTime.of(18, 0));

                LocalDate end = amenity.getRcptEndDate().toLocalDate();

                if (end.isAfter(currentDate.plusMonths(1))) {
                    end = currentDate.plusMonths(1);
                    end = end.withDayOfMonth(end.lengthOfMonth());
                }

                if (roundService.addRound(amenity.getId(), amenity.getAdmin().getAdminId(), round1)) {
                    for (LocalDate date = currentDate; !date.isAfter(end); date = date.plusDays(1)) {
                        if (isWeekday(date)) {
                            RoundCell roundCell = new RoundCell();

                            roundCell.setRoundCellState(RoundCellState.RESERVE_START);
                            roundCell.setRoundCellDate(date);
                            roundCell.setRound(round1);
                            roundCellService.addRoundCell(roundCell);
                        }
                    }
                }

                /*
                if (roundService.addRound(amenity.getId(), amenity.getAdmin().getAdminId(), round2)) {
                    for (LocalDate date = currentDate; !date.isAfter(end); date = date.plusDays(1)) {
                        if (isWeekday(date)) {
                            RoundCell roundCell = new RoundCell();

                            roundCell.setRoundCellState(RoundCellState.RESERVE_START);
                            roundCell.setRoundCellDate(date);
                            roundCell.setRound(round1);
                            roundCellService.addRoundCell(roundCell);
                        }

                    }
                }

                 */


                count++;
            }
        }


        return count;

    }

    public boolean isWeekday(LocalDate date) {
        // LocalDate 객체에서 DayOfWeek 얻기
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        // 요일이 월요일(1)부터 금요일(5)까지인지 확인
        //return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
        return dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.WEDNESDAY || dayOfWeek == DayOfWeek.FRIDAY;
    }

    public AmenityResponseDto selectAmenity(Long id, String username) {

        AmenityResponseDto dto = new AmenityResponseDto();

        Optional<Amenity> amenity = amenityDAO.selectAmenityWhereAdminId(id, username);

        return amenity.map(dto::fromEntity).orElse(null);

    }

    public List<AmenityListResponseDto> selectAmenityList(String username) {

        List<AmenityListResponseDto> dtoList = new ArrayList<>();

        List<Amenity> amenityList = amenityDAO.selectAmenityList(username);

        for (Amenity amenity : amenityList) {
            AmenityListResponseDto dto = new AmenityListResponseDto();
            dtoList.add(dto.fromEntity(amenity));
        }

        return dtoList;

    }

    public boolean updateAmenity(@RequestBody AmenityUpdateRequestDto dto) {
        return amenityDAO.updateAmenity(dto);
    }

    public boolean deleteAmenity(Long id, String username) {
        return amenityDAO.deleteAmenity(id, username);
    }


}
