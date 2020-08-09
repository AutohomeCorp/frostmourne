package com.autohome.frostmourne.monitor.contract.enums;

public interface AlertTemplateEnums {

    /**
     * 模板类型
     */
    enum TemplateType {
        /**
         * 通用模板
         */
        COMMON("通用"),
        /**
         * 数据源模板
         */
        DATA_NAME("数据");

        private String displanName;

        TemplateType(String displanName) {
            this.displanName = displanName;
        }

        public String getDisplanName() {
            return displanName;
        }

    }

}
