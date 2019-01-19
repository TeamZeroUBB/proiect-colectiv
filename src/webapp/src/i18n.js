import i18n from "i18next";
import { reactI18nextModule } from "react-i18next";

import translationEN from './locales/en/translation';
import translationRO from './locales/ro/translation';
import translationDE from './locales/de/translation'

// the translations
const resources = {
    US: {
        translation: translationEN
    },
    RO: {
        translation: translationRO
    },
    DE: {
        translation: translationDE
    }
};

i18n
    .use(reactI18nextModule) // passes i18n down to react-i18next
    .init({
        resources,
        lng: "US",

        keySeparator: false, // we do not use keys in form messages.welcome

        interpolation: {
            escapeValue: false // react already safes from xss
        }
    });

export default i18n;