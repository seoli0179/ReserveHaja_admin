package com.example.reservehaja.controller;

import com.example.reservehaja.data.dto.round.RoundRequestDto;
import com.example.reservehaja.data.dto.round.RoundResponseDto;
import com.example.reservehaja.data.dto.round.RoundUpdatedRequestDto;
import com.example.reservehaja.data.entity.Round;
import com.example.reservehaja.data.entity.RoundCell;
import com.example.reservehaja.data.state.RoundCellState;
import com.example.reservehaja.service.round.RoundService;
import com.example.reservehaja.service.roundCell.RoundCellService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/round")
@RequiredArgsConstructor
public class RoundController {

    private final RoundService roundService;
    private final RoundCellService roundCellService;

    @PostMapping("round")
    public boolean roundCreate(@RequestBody RoundRequestDto dto) {

        System.out.println(dto.toString());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                Round round = dto.toEntity();
                if (roundService.addRound(dto.getAmenityId(), username, round)) {
                    for (int i = 0; i < dto.getRoundDates().size(); i++) {
                        RoundCell roundCell = new RoundCell();
                        roundCell.setRoundCellState(RoundCellState.RESERVE_START);
                        roundCell.setRoundCellDate(dto.getRoundDates().get(i));
                        roundCell.setRound(round);
                        roundCellService.addRoundCell(roundCell);
                    }
                    return true;
                }


            }
        }
        return false;
    }

    @GetMapping("round")
    public RoundResponseDto roundRead(@Param("id") Long id, @Param("amenityId") Long amenityId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // authentication 객체로부터 사용자 정보를 추출합니다.
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // 사용자의 이름, 권한 등을 여기에서 얻을 수 있습니다.
                String username = userDetails.getUsername();
                // 다른 정보도 사용 가능, 예: userDetails.getAuthorities()

                return roundService.readRound(id, amenityId, username);
                // 여기에서 username 또는 다른 사용자 세부 정보를 사용합니다.
                // 예: 데이터베이스 조회, 로그 남기기 등
            }
        }
        return null;
    }


    @PutMapping("round")
    public boolean roundUpdate(@RequestBody RoundUpdatedRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // authentication 객체로부터 사용자 정보를 추출합니다.
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // 사용자의 이름, 권한 등을 여기에서 얻을 수 있습니다.
                String username = userDetails.getUsername();
                // 다른 정보도 사용 가능, 예: userDetails.getAuthorities()

                return roundService.updateRound(dto, username);
                // 여기에서 username 또는 다른 사용자 세부 정보를 사용합니다.
                // 예: 데이터베이스 조회, 로그 남기기 등
            }
        }
        return false;
    }

    @GetMapping("roundList")
    public List<RoundResponseDto> roundRead(@Param("id") Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // authentication 객체로부터 사용자 정보를 추출합니다.
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // 사용자의 이름, 권한 등을 여기에서 얻을 수 있습니다.
                String username = userDetails.getUsername();
                // 다른 정보도 사용 가능, 예: userDetails.getAuthorities()

                return roundService.readRoundArray(id, username);
                // 여기에서 username 또는 다른 사용자 세부 정보를 사용합니다.
                // 예: 데이터베이스 조회, 로그 남기기 등
            }
        }
        return null;
    }

    @DeleteMapping("round")
    public boolean roundDelete(@Param("id") Long id) {
        System.out.println(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // authentication 객체로부터 사용자 정보를 추출합니다.
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // 사용자의 이름, 권한 등을 여기에서 얻을 수 있습니다.
                String username = userDetails.getUsername();
                // 다른 정보도 사용 가능, 예: userDetails.getAuthorities()

                return roundService.deleteRound(id, username);
                // 여기에서 username 또는 다른 사용자 세부 정보를 사용합니다.
                // 예: 데이터베이스 조회, 로그 남기기 등
            }
        }
        return false;
    }

}
