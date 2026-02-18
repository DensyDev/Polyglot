package org.densy.polyglot.common.language.converter;

import org.densy.polyglot.common.language.LocaleLanguage;
import org.densy.polyglot.common.language.SimpleLanguage;

/**
 * Converter between SimpleLanguage and LocaleLanguage.
 */
public class SimpleLocaleLanguageConverter extends AbstractLanguageConverter {

    public SimpleLocaleLanguageConverter() {
        // English
        addMapping(SimpleLanguage.ENG, LocaleLanguage.EN_GB);
        addMapping(SimpleLanguage.ENG, LocaleLanguage.EN_US);

        // Spanish
        addMapping(SimpleLanguage.SPA, LocaleLanguage.ES_ES);
        addMapping(SimpleLanguage.SPA, LocaleLanguage.ES_MX);

        // French
        addMapping(SimpleLanguage.FRA, LocaleLanguage.FR_FR);
        addMapping(SimpleLanguage.FRA, LocaleLanguage.FR_CA);

        // Portuguese
        addMapping(SimpleLanguage.POR, LocaleLanguage.PT_BR);
        addMapping(SimpleLanguage.POR, LocaleLanguage.PT_PT);

        // Chinese
        addMapping(SimpleLanguage.ZHO, LocaleLanguage.ZH_CN);
        addMapping(SimpleLanguage.ZHO, LocaleLanguage.ZH_TW);

        // Core European languages
        addMapping(SimpleLanguage.DEU, LocaleLanguage.DE_DE);
        addMapping(SimpleLanguage.ITA, LocaleLanguage.IT_IT);
        addMapping(SimpleLanguage.NLD, LocaleLanguage.NL_NL);
        addMapping(SimpleLanguage.BUL, LocaleLanguage.BG_BG);
        addMapping(SimpleLanguage.CES, LocaleLanguage.CS_CZ);
        addMapping(SimpleLanguage.DAN, LocaleLanguage.DA_DK);
        addMapping(SimpleLanguage.ELL, LocaleLanguage.EL_GR);
        addMapping(SimpleLanguage.FIN, LocaleLanguage.FI_FI);
        addMapping(SimpleLanguage.HUN, LocaleLanguage.HU_HU);
        addMapping(SimpleLanguage.NOR, LocaleLanguage.NB_NO);
        addMapping(SimpleLanguage.POL, LocaleLanguage.PL_PL);
        addMapping(SimpleLanguage.SLK, LocaleLanguage.SK_SK);
        addMapping(SimpleLanguage.SWE, LocaleLanguage.SV_SE);
        addMapping(SimpleLanguage.TUR, LocaleLanguage.TR_TR);
        addMapping(SimpleLanguage.RON, LocaleLanguage.RO_RO);

        // Slavic languages
        addMapping(SimpleLanguage.RUS, LocaleLanguage.RU_RU);
        addMapping(SimpleLanguage.UKR, LocaleLanguage.UK_UA);
        addMapping(SimpleLanguage.BEL, LocaleLanguage.BE_BY);
        addMapping(SimpleLanguage.SLV, LocaleLanguage.SL_SI);
        addMapping(SimpleLanguage.HRV, LocaleLanguage.HR_HR);
        addMapping(SimpleLanguage.SRP, LocaleLanguage.SR_RS);
        addMapping(SimpleLanguage.BOS, LocaleLanguage.BS_BA);
        addMapping(SimpleLanguage.MKD, LocaleLanguage.MK_MK);

        // Central Asia
        addMapping(SimpleLanguage.KAZ, LocaleLanguage.KK_KZ);

        // East / South-East Asia
        addMapping(SimpleLanguage.JPN, LocaleLanguage.JA_JP);
        addMapping(SimpleLanguage.KOR, LocaleLanguage.KO_KR);
        addMapping(SimpleLanguage.THA, LocaleLanguage.TH_TH);
        addMapping(SimpleLanguage.VIE, LocaleLanguage.VI_VN);
        addMapping(SimpleLanguage.FIL, LocaleLanguage.FIL_PH);
        addMapping(SimpleLanguage.IND, LocaleLanguage.ID_ID);

        // Middle East
        addMapping(SimpleLanguage.ARA, LocaleLanguage.AR_SA);
        addMapping(SimpleLanguage.FAS, LocaleLanguage.FA_IR);
        addMapping(SimpleLanguage.HEB, LocaleLanguage.HE_IL);
        addMapping(SimpleLanguage.URD, LocaleLanguage.UR_PK);

        // South Asia
        addMapping(SimpleLanguage.HIN, LocaleLanguage.HI_IN);
        addMapping(SimpleLanguage.BEN, LocaleLanguage.BN_BD);
        addMapping(SimpleLanguage.TAM, LocaleLanguage.TA_IN);
        addMapping(SimpleLanguage.TEL, LocaleLanguage.TE_IN);
        addMapping(SimpleLanguage.MAR, LocaleLanguage.MR_IN);

        // Africa
        addMapping(SimpleLanguage.SWA, LocaleLanguage.SW_KE);
        addMapping(SimpleLanguage.AMH, LocaleLanguage.AM_ET);
    }
}