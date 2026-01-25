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
        // Other
        addMapping(SimpleLanguage.GER, LocaleLanguage.DE_DE);
        addMapping(SimpleLanguage.ITA, LocaleLanguage.IT_IT);
        addMapping(SimpleLanguage.JPN, LocaleLanguage.JA_JP);
        addMapping(SimpleLanguage.KOR, LocaleLanguage.KO_KR);
        addMapping(SimpleLanguage.POR, LocaleLanguage.PT_BR);
        addMapping(SimpleLanguage.POR, LocaleLanguage.PT_PT);
        addMapping(SimpleLanguage.RUS, LocaleLanguage.RU_RU);
        addMapping(SimpleLanguage.CHI, LocaleLanguage.ZH_CN);
        addMapping(SimpleLanguage.CHI, LocaleLanguage.ZH_TW);
        addMapping(SimpleLanguage.DUT, LocaleLanguage.NL_NL);
        addMapping(SimpleLanguage.BUL, LocaleLanguage.BG_BG);
        addMapping(SimpleLanguage.CZE, LocaleLanguage.CS_CZ);
        addMapping(SimpleLanguage.DAN, LocaleLanguage.DA_DK);
        addMapping(SimpleLanguage.GRE, LocaleLanguage.EL_GR);
        addMapping(SimpleLanguage.FIN, LocaleLanguage.FI_FI);
        addMapping(SimpleLanguage.HUN, LocaleLanguage.HU_HU);
        addMapping(SimpleLanguage.IND, LocaleLanguage.ID_ID);
        addMapping(SimpleLanguage.NOR, LocaleLanguage.NB_NO);
        addMapping(SimpleLanguage.POL, LocaleLanguage.PL_PL);
        addMapping(SimpleLanguage.SLO, LocaleLanguage.SK_SK);
        addMapping(SimpleLanguage.SWE, LocaleLanguage.SV_SE);
        addMapping(SimpleLanguage.TUR, LocaleLanguage.TR_TR);
        addMapping(SimpleLanguage.UKR, LocaleLanguage.UK_UA);
    }
}
