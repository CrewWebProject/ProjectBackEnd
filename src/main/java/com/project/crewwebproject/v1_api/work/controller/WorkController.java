package com.project.crewwebproject.v1_api.work.controller;

//@RestController
//@RequiredArgsConstructor
//@RequestMapping("v1/work")
//public class WorkController {
//    private final WorkService workService;
//
//    @ApiOperation(value = "GET Work 메인 노출 data" , notes = "카테고리별 work 탭 노출 데이터 리턴 / categoryCode : { CN0 = 전체 } { CN1 = 지원사업 } { CN2 = 공연 }")
//    @GetMapping("/main")
//    public ResponseEntity<PrivateResponseBody> postPerformance(@RequestParam String categoryCode){
//        List<WorkMainResponseDto> mainWork = workService.getMainWork(categoryCode);
//        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , (mainWork) ),HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "POST Work 상세 data 저장" , notes = "카테고리별 work 탭 노출 데이터 저장 / workNo : 해당 work의 number / categoryCode : { CN1 = 지원사업 } { CN2 = 공연 } / workDetailSaveDto : work 상세보기 저장에 필요한 data들")
//    @PostMapping("/save")
//    public ResponseEntity<PrivateResponseBody> postPerformance(@RequestParam String workNo ,
//                                                               @RequestParam String categoryCode ,
//                                                               @RequestBody WorkDetailSaveRequestDto workDetailSaveRequestDto){
//        workService.saveWorkDetail(workNo , categoryCode , workDetailSaveRequestDto);
//        return new ResponseEntity<>(new PrivateResponseBody(StatusCode.OK , (null) ),HttpStatus.OK);
//    }
//}
