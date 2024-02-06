package com.example.reservehaja.controller;

import com.example.reservehaja.data.dto.reserve.ReserveResponseDto;
import com.example.reservehaja.data.dto.roundCell.RoundCellResponseDto;
import com.example.reservehaja.data.state.ReserveState;
import com.example.reservehaja.data.state.RoundCellState;
import com.example.reservehaja.service.reserve.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {

    private final ReserveService reserveService;

    @GetMapping("/reservelist")
    public List<ReserveResponseDto> reserveListRead(@RequestParam("roundcellid") Long roundCellId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // authentication 객체로부터 사용자 정보를 추출합니다.
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // 사용자의 이름, 권한 등을 여기에서 얻을 수 있습니다.
                String username = userDetails.getUsername();
                // 다른 정보도 사용 가능, 예: userDetails.getAuthorities()

                return reserveService.readReserveList(roundCellId);
                // 여기에서 username 또는 다른 사용자 세부 정보를 사용합니다.
                // 예: 데이터베이스 조회, 로그 남기기 등
            }
        }
        return null;
    }

    @PutMapping("/reserve/state")
    public boolean reserveStateUpdate(@RequestBody Map<String, Object> data) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                String username = userDetails.getUsername();

                Long reserveId = Long.valueOf(data.get("reserveId").toString());
                ReserveState reserveState = ReserveState.valueOf(data.get("reserveState").toString());

                System.out.println(reserveId);
                System.out.println(reserveState);

                return reserveService.updateReserveState(reserveId, reserveState, username);
            }
        }


        return false;
    }

}
