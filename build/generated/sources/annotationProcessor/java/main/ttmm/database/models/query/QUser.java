package ttmm.database.models.query;

import io.ebean.typequery.PBoolean;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.PTimestamp;
import ttmm.database.models.User;

/**
 * Query bean for User.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@io.ebean.typequery.Generated("io.ebean.querybean.generator")
@io.ebean.typequery.TypeQueryBean("v1")
public final class QUser extends io.ebean.typequery.TQRootBean<User,QUser> {

  private static final QUser _alias = new QUser(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QUser alias() {
    return _alias;
  }

  public PLong<QUser> id;
  public PTimestamp<QUser> createdAt;
  public PTimestamp<QUser> updatedAt;
  public PBoolean<QUser> deleted;
  public PString<QUser> first_name;
  public PString<QUser> last_name;
  public PString<QUser> email;
  public PString<QUser> avatar;
  public PString<QUser> google_auth_id;


  /**
   * Return a query bean used to build a FetchGroup.
   * <p>
   * FetchGroups are immutable and threadsafe and can be used by many
   * concurrent queries. We typically stored FetchGroup as a static final field.
   * <p>
   * Example creating and using a FetchGroup.
   * <pre>{@code
   * 
   * static final FetchGroup<Customer> fetchGroup = 
   *   QCustomer.forFetchGroup()
   *     .shippingAddress.fetch()
   *     .contacts.fetch()
   *     .buildFetchGroup();
   * 
   * List<Customer> customers = new QCustomer()
   *   .select(fetchGroup)
   *   .findList();
   * 
   * }</pre>
   */
  public static QUser forFetchGroup() {
    return new QUser(io.ebean.FetchGroup.queryFor(User.class));
  }

  /** Construct using the default Database */
  public QUser() {
    super(User.class);
  }

  /** Construct with a given transaction */
  public QUser(io.ebean.Transaction transaction) {
    super(User.class, transaction);
  }

  /** Construct with a given Database */
  public QUser(io.ebean.Database database) {
    super(User.class, database);
  }


  /** Private constructor for Alias */
  private QUser(boolean dummy) {
    super(dummy);
  }

  /** Private constructor for FetchGroup building */
  private QUser(io.ebean.Query<User> fetchGroupQuery) {
    super(fetchGroupQuery);
  }

  /** Private constructor for filterMany */
  private QUser(io.ebean.ExpressionList<User> filter) {
    super(filter);
  }

  /**
   * Provides static properties to use in <em> select() and fetch() </em>
   * clauses of a query. Typically referenced via static imports. 
   */
  @io.ebean.typequery.Generated("io.ebean.querybean.generator")
  public static final class Alias {
    public static PLong<QUser> id = _alias.id;
    public static PTimestamp<QUser> createdAt = _alias.createdAt;
    public static PTimestamp<QUser> updatedAt = _alias.updatedAt;
    public static PBoolean<QUser> deleted = _alias.deleted;
    public static PString<QUser> first_name = _alias.first_name;
    public static PString<QUser> last_name = _alias.last_name;
    public static PString<QUser> email = _alias.email;
    public static PString<QUser> avatar = _alias.avatar;
    public static PString<QUser> google_auth_id = _alias.google_auth_id;
  }

  /**  Association query bean */
  @io.ebean.typequery.Generated("io.ebean.querybean.generator")
  @io.ebean.typequery.TypeQueryBean("v1")
  public static final class Assoc<R> extends io.ebean.typequery.TQAssocBean<User,R,QUser> {
    public PLong<R> id;
    public PTimestamp<R> createdAt;
    public PTimestamp<R> updatedAt;
    public PBoolean<R> deleted;
    public PString<R> first_name;
    public PString<R> last_name;
    public PString<R> email;
    public PString<R> avatar;
    public PString<R> google_auth_id;

    public Assoc(String name, R root) {
      super(name, root);
    }

    public Assoc(String name, R root, String prefix) {
      super(name, root, prefix);
    }

    public final R filterMany(java.util.function.Consumer<QUser> apply) {
      final io.ebean.ExpressionList list = io.ebean.Expr.factory().expressionList();
      final var qb = new QUser(list);
      apply.accept(qb);
      expr().filterMany(_name).addAll(list);
      return _root;
    }
  }
}
