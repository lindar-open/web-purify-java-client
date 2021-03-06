package com.lindar.webpurify.api;

import com.lindar.webpurify.util.Messages;
import com.lindar.webpurify.util.Methods;
import com.lindar.webpurify.util.Params;
import com.lindar.webpurify.util.configs.WebPurifyConfigs;
import com.lindar.webpurify.util.enums.LanguageEnum;
import com.lindar.webpurify.util.enums.RequestTypeEmum;
import com.lindar.wellrested.vo.Result;
import com.lindar.wellrested.vo.ResultBuilder;

import java.util.Map;

public class CheckResource extends AbstractResource {

    public CheckResource(WebPurifyConfigs webPurifyConfigs) {
        super(webPurifyConfigs);
    }

    public CheckRequest text(String text) {
        return new CheckRequest(text);
    }

    public class CheckRequest {

        private Map<String, String> formParams;

        CheckRequest(String text) {
            formParams = buildFormParams(Methods.CHECK, text);
        }

        public CheckRequest language(LanguageEnum language) {
            this.formParams.put(Params.LANGUAGE, language.getCode());
            return this;
        }

        public CheckRequest filterEmails() {
            this.formParams.put(Params.EMAIL, "1");
            return this;
        }

        public CheckRequest filterPhoneNumbers() {
            this.formParams.put(Params.PHONE, "1");
            return this;
        }

        public CheckRequest filterLinks() {
            this.formParams.put(Params.LINKS, "1");
            return this;
        }

        public Result<Boolean> submit() {
            if (!isEnabled()) {
                return ResultBuilder.failed(Messages.ERROR.DISABLED);
            }
            return sendRequest(RequestTypeEmum.CHECK, formParams, Messages.SUCCESS.TEXT_CHECKED);
        }

    }

}
