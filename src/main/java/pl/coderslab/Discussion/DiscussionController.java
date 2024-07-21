package pl.coderslab.Discussion;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
//@RestController
//@RequestMapping("/discussion")
//@RequiredArgsConstructor
@Controller
public class DiscussionController {

    private final DiscussionDao discussionDao;

    public DiscussionController(DiscussionDao discussionDao) {
        this.discussionDao = discussionDao;
    }

//    @RequestMapping("/discussion/add")
//    @ResponseBody
//    public String addDiscussion() {
//        Discussion discussion = new Discussion();
//        discussion.setComment("Dobra pogoda na obserwacje");
//        discussionDao.saveDiscussion(discussion);
//        return "Id dodanej dyskusji to: " + discussion.getId() + "\nSkomentowano: " + discussion.getComment();
//    }



//    @RequestMapping("/discussion/get/{id}")
//    @ResponseBody
//    public String getDiscussionById(@PathVariable Long id) {
//        Discussion discussion = discussionDao.findDiscussionById(id);
//        return discussion.toString();
//    }

//    @RequestMapping("/discussion/update/{id}")
//    @ResponseBody
//    public String updateDiscussion(@PathVariable Long id, @PathVariable String comment) {
//        Discussion discussion = discussionDao.findDiscussionById(id);
//        discussion.setComment(comment);
//        discussionDao.updateDiscussion(discussion);
//        return discussion.toString();
//    }

    @RequestMapping("discussion/delete/{id}")
    @ResponseBody
    public String deleteDiscussion(@PathVariable Long id) {
        Discussion discussion = discussionDao.findDiscussionById(id);
        discussionDao.deleteDiscussionById(discussion.getId());
        return "Deleted" + discussion;
    }

    @RequestMapping("/discussion/all")
    @ResponseBody
    public String findAllDiscussions() {
        List<Discussion> allDiscussions = discussionDao.findAllDiscussions();
        allDiscussions.forEach(discussion -> log.info(discussion.toString()));
        return "findAllDiscussion";
    }
}
