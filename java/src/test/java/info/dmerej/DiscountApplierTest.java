package info.dmerej;

import org.junit.jupiter.api.Test;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class DiscountApplierTest {
  int discount = 10;
  List<User> users = new ArrayList<User>();
  List<User> test = new ArrayList<User>();

  Notifier notifier = new Notifier() {
    @Override
    public void notify(User user, String message) {
      test.add(user);
      System.out.println(user.email() + " : " + message);
    }
  };

  @Test
  void should_notify_twice_when_applying_discount_for_two_users_v1() {
    test.clear();

    users.add(new User("test", "test.test@test.ts"));
    users.add(new User("fred", "fred.cas@test.ts"));
    users.add(new User("john", "john.vla@test.ts"));

    DiscountApplier discountApplier = new DiscountApplier(notifier);
    discountApplier.applyV1(discount, users);
    assert test.size() == users.size();
  }

  @Test
  void should_notify_twice_when_applying_discount_for_two_users_v2() {
    test.clear();

    users.add(new User("test", "test.test@test.ts"));
    users.add(new User("fred", "fred.cas@test.ts"));
    users.add(new User("john", "john.vla@test.ts"));

    DiscountApplier discountApplier = new DiscountApplier(notifier);
    discountApplier.applyV2(discount, users);

    assert test.equals(users);
  }
}
