package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.service.annotation.PostExchange;

@Controller
public class ConverterController {

    @GetMapping("/converter-view")
    public String converterView(Model model) {
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", new IpPort("127.0.0.1", 8080));
        return "converter-view";
    }

    @GetMapping("/converter/edit")
    public String converterForm(Model model) {
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        Form form = new Form(ipPort);
        model.addAttribute("form", form);
        return "converter-form";
    }

    //th:field의 사용법은 <form> 내의 th:object의 객체에 접근해서 th:field=*{필드명}과 같이 특정 필드에 접근하여 출력하는 용도로 사용합니다.
    //그렇기 때문에 converter-form.html 에 th:field=*{ipPort} 를 사용할 수 있다. (Form.class 내에 해당 객체가 있으므로)

    @PostMapping("/converter/edit")
    public String converterEdit(@ModelAttribute Form form, Model model) {
        IpPort ipPort = form.getIpPort();
        model.addAttribute("ipPort", ipPort);
        return "converter-view";
    }

    //해당 컨트롤러는 문자인 "127.0.0.1:8080" 을 컨버터를 사용하여 Form 객체로 변환되어 받아들이게 되는데, 이는 @ModelAttribute 가 문자열을 받았을 때,
    //컨버터를 서칭하여 사용하기 때문이다.

    @Data
    static class Form {
        private IpPort ipPort;

        public Form(IpPort ipPort) {
            this.ipPort = ipPort;
        }
    }

}
