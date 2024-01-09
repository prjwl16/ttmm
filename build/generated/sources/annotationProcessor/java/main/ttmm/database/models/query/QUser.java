package ttmm.database.models.query;

import io.ebean.typequery.PBoolean;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.PTimestamp;
import ttmm.database.models.User;
import ttmm.database.models.query.QGroup;

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
  public PString<QUser> firstName;
  public PString<QUser> lastName;
  public PString<QUser> email;
  public PString<QUser> avatar;
  public PString<QUser> googleAuthId;
  public QGroup.Assoc<QUser> groups;


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
    public static PString<QUser> firstName = _alias.firstName;
    public static PString<QUser> lastName = _alias.lastName;
    public static PString<QUser> email = _alias.email;
    public static PString<QUser> avatar = _alias.avatar;
    public static PString<QUser> googleAuthId = _alias.googleAuthId;
    public static QGroup.Assoc<QUser> groups = _alias.groups;
  }

  /**  Association query bean */
  @io.ebean.typequery.Generated("io.ebean.querybean.generator")
  @io.ebean.typequery.TypeQueryBean("v1")
  public static final class Assoc<R> extends io.ebean.typequery.TQAssocBean<User,R,QUser> {
    public PLong<R> id;
    public PTimestamp<R> createdAt;
    public PTimestamp<R> updatedAt;
    public PBoolean<R> deleted;
    public PString<R> firstName;
    public PString<R> lastName;
    public PString<R> email;
    public PString<R> avatar;
    public PString<R> googleAuthId;
    public QGroup.Assoc<R> groups;

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
