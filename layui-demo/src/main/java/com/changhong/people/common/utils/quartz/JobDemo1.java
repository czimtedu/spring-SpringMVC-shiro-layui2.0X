package com.changhong.people.common.utils.quartz;

import java.io.Serializable;

public class JobDemo1 implements Serializable{  
    /**
	 * @Field @serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private static int i = 0;  
    public void doJobDemo1() {
    	System.out.println(Thread.currentThread().getName()+"------------------");
        System.out.println("不继承org.springframework.scheduling.quartz.QuartzJobBean类，每隔指定时间则触发一次,第" + ++i + "次调用进行中...");  
    }  
}  
