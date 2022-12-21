package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member{

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // 1대N 양방향 연관관계 방법
    // fetch = FetchType.EAGER : 즉시 로딩
    // Member와 Team 중에 Member와 team이 90% 이상 같이 사용될 경우
    // 즉시 로딩을 사용
    // 즉시 로딩을 사용될 경우 Member와 Team이 조인으로 쿼리를 한번에 조회 된다.
    // 실무에서 절대 사용하면 안된다. 무조건 지연 로딩으로 구현해야 한다.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    // 사용할 임베디드 타입을 지정
    @Embedded
    private Period period;
    @Embedded
    private Address homeAddress;
    
    
    // 값 타입 컬렉션
    @ElementCollection
    @JoinTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name = "member_id"))
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @JoinTable(name = "ADDRESS", joinColumns = @JoinColumn(name = "member_id"))
    private List<Address> addressHistory = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }


    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
    }
}
