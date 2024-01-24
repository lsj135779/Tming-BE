package com.spring.tming.domain.applicant.validator;

import com.spring.tming.domain.applicant.entity.Applicant;
import com.spring.tming.global.exception.GlobalException;
import com.spring.tming.global.meta.ResultCode;

public class ApplicantValidator {

    public static void checkAlreadyApplied(Applicant applicant) {
        if (isExistApplicant(applicant)) {
            throw new GlobalException(ResultCode.ALREADY_APPLIED_POST);
        }
    }

    public static void checkNotYetApplied(Applicant applicant) {
        if (!isExistApplicant(applicant)) {
            throw new GlobalException(ResultCode.NOT_YET_APPLIED_POST);
        }
    }

    public static void checkCancelBeforeAdmit(Applicant applicant) {
        if (!isNullApplicant(applicant)) {
            throw new GlobalException(ResultCode.NOT_FOUND_APPLICANT);
        }
    }

    private static boolean isNullApplicant(Applicant applicant) {
        return applicant == null;
    }

    private static boolean isExistApplicant(Applicant applicant) {
        return applicant != null;
    }
}
