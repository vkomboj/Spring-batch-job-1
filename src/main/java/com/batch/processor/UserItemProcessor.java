package com.batch.processor;

import com.batch.model.User;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;

@Component
public class UserItemProcessor implements ItemProcessor<User, User> {
	
	@Override
	public User process(User user) throws Exception {
		//put validation
		
		User user1 = new User(user.getUserId(), user.getFirstName(), user.getLastName(), user.getPhone(),user.getMailId());
		return user1;
	}
} 

//public Customer process(final Customer item) throws Exception {
//    if (new GregorianCalendar().get(Calendar.MONTH) == item.getBirthday().get(Calendar.MONTH)) {
//        return item;
//    }
//    return null;
//}