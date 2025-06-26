package org.example.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Member;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class JsonMemberRepository implements MemberRepository {
    private final List<Member> members = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper(); // Jackson 라이브러리의 JSON 직렬화 및 역직렬화 도구
    private final File file = new File("members.json");

    public JsonMemberRepository(){
        loadFromJson();
    }

    // json -> List<Member> (1)
    private void loadFromJson(){
        try {
            if (file.exists()) {
                List<Member> loaded =
                        mapper.readValue(file, new TypeReference<List<Member>>() {
                        });
                members.clear();
                members.addAll(loaded);
            }
        } catch (Exception e) {
            throw new RuntimeException("json 로드 실패", e);
        }

    }

    // List<Member> -> json
    private void saveToJson(){
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, members);
        } catch (Exception e) {
            throw new RuntimeException("json 저장 실패");
        }

    }

    @Override
    public void save(Member member) {
        members.add(member);
        saveToJson();

    }

    @Override
    public void update(Member member){
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getId().equals(member.getId())) {
                members.set(i, member);
                break;
            }
        }
        saveToJson();  // 변경된 내용을 JSON 파일에 반영

    }

    @Override
    public Member findByLoginId(String loginId) {
        return members.stream()
                .filter(x -> x.getLoginId().equals(loginId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(members);
    }
}
