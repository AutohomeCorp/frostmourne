package com.autohome.frostmourne.monitor.controller;

import java.util.Optional;

import javax.annotation.Resource;

import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.domain.generate.ShortLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.autohome.frostmourne.monitor.controller.annotation.PermissionLimit;
import com.autohome.frostmourne.monitor.dao.mybatis.frostmourne.repository.IShortLinkRepository;

@Controller
public class IndexController {

    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private IShortLinkRepository shortLinkRepository;

    @PermissionLimit(limit = false)
    @RequestMapping(value = "/goto/{id}", method = RequestMethod.GET)
    public String jump(@PathVariable(value = "id") Long id) {
        if (id == null) {
            LOGGER.error("invalid short link id: {}", id);
            return "redirect:/dashboard.view";
        }
        Optional<ShortLink> shortLinkOptional = shortLinkRepository.selectByPrimaryKey(id);
        if (shortLinkOptional.isPresent()) {
            return String.format("redirect:%s", shortLinkOptional.get().getLongLink());
        } else {
            LOGGER.error("invalid short link id: {}", id);
            return "redirect:/dashboard.view";
        }
    }
}
