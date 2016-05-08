package test;

import org.junit.Test;

import com.chen.PageModel.BackupFile;
import com.chen.util.BackupFileUtil;

public class TestList {
 
	@Test
	public void testBackup(){
		BackupFileUtil bfu = BackupFileUtil.getInstance(" ");
		for(BackupFile bf:bfu.listBackups()){
			System.out.println(bf);
		}
	}
	
	@Test
	public void testBackup1(){
		BackupFileUtil bfu = BackupFileUtil.getInstance("E:\\image");
		//bfu.backup("我的备份");
		bfu.resume("1451530273056_我的备份.tar.gz");
	}
}
