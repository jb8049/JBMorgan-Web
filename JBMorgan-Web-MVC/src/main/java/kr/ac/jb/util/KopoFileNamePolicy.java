package kr.ac.jb.util;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class KopoFileNamePolicy implements FileRenamePolicy {
	// 사용자가 파일을 첨부하면, 이것에 의해 파일명이 변경된 뒤에 저장되도록함
	@Override
	public File rename(File f) {
		String name = f.getName();
		String ext = "";
		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			ext = name.substring(dot); 
		} else {
			ext = "";
		}
		String str = "kopo" + UUID.randomUUID(); // 32비트의 수를 추출
		File renameFile = new File(f.getParent(), str + ext);
		return renameFile;
	}
}
