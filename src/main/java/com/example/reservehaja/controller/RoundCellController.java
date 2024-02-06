package com.example.reservehaja.controller;

import com.example.reservehaja.data.dto.round.RoundRequestDto;
import com.example.reservehaja.data.dto.round.RoundResponseDto;
import com.example.reservehaja.data.dto.round.RoundUpdatedRequestDto;
import com.example.reservehaja.data.dto.roundCell.RoundCellResponseDto;
import com.example.reservehaja.data.entity.Round;
import com.example.reservehaja.data.entity.RoundCell;
import com.example.reservehaja.data.repo.RoundCellRepository;
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
import java.util.Map;

@RestController
@RequestMapping("/roundcell")
@RequiredArgsConstructor
public class RoundCellController {

    private final RoundCellService roundCellService;

    @GetMapping("roundcelllist")
    public List<RoundCellResponseDto> roundCellListRead(@Param("roundId") Long roundId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // authentication 객체로부터 사용자 정보를 추출합니다.
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // 사용자의 이름, 권한 등을 여기에서 얻을 수 있습니다.
                String username = userDetails.getUsername();
                // 다른 정보도 사용 가능, 예: userDetails.getAuthorities()

                return roundCellService.readRoundCellList(roundId, username);
                // 여기에서 username 또는 다른 사용자 세부 정보를 사용합니다.
                // 예: 데이터베이스 조회, 로그 남기기 등
            }
        }
        return null;
    }

    @PutMapping("/roundcell/state")
    public boolean roundCellStateUpdate(@RequestBody Map<String, Object> data) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // authentication 객체로부터 사용자 정보를 추출합니다.
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // 사용자의 이름, 권한 등을 여기에서 얻을 수 있습니다.
                String username = userDetails.getUsername();
                // 다른 정보도 사용 가능, 예: userDetails.getAuthorities()

                Long roundCellId = Long.valueOf(data.get("roundCellId").toString());
                RoundCellState roundCellState = RoundCellState.valueOf(data.get("roundCellState").toString());

                System.out.println(roundCellId);
                System.out.println(roundCellState);

                return roundCellService.updateRoundCellState(roundCellId, roundCellState, username);
                // 여기에서 username 또는 다른 사용자 세부 정보를 사용합니다.
                // 예: 데이터베이스 조회, 로그 남기기 등
            }
        }


        return false;
    }


}
