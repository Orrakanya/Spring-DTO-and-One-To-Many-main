package ku.cs.kafe.controller;

import ku.cs.kafe.model.MenuRequest;
import ku.cs.kafe.service.CategoryService;
import ku.cs.kafe.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/menus")// mapping สำหรับทุก ๆ function
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    // table page
    public String getAllMenus(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        // model.addAttribute("menus", menuService.getAllMenus()); // menus ชื่อที่ใช้ใน file html
        return "menu-all";
        // return string เสมอ
    }

    // การเพิ่ม menu
    @GetMapping("/add")
    public String getMenuForm(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "menu-add";
    }

    // หลังจากกด add แล้วจะเป็นหน้านี้
    @PostMapping("/add")
    public String createMenu(@ModelAttribute MenuRequest menu, Model model) {
        menuService.createMenu(menu);
        model.addAttribute("categories", categoryService.getAllCategories());
        // model.addAttribute("menus", menuService.getAllMenus());
        return "redirect:/menus";
    }

}
