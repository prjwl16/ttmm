package ttmm.database.models.query;

import io.ebean.typequery.PBoolean;
import io.ebean.typequery.PLong;
import io.ebean.typequery.PString;
import io.ebean.typequery.PTimestamp;
import ttmm.database.models.Group;

/**
 * Query bean for Group.
 * 
 * THIS IS A GENERATED OBJECT, DO NOT MODIFY THIS CLASS.
 */
@io.ebean.typequery.Generated("io.ebean.querybean.generator")
@io.ebean.typequery.TypeQueryBean("v1")
public final class QGroup extends io.ebean.typequery.TQRootBean<Group,QGroup> {

  private static final QGroup _alias = new QGroup(true);

  /**
   * Return the shared 'Alias' instance used to provide properties to 
   * <code>select()</code> and <code>fetch()</code> 
   */
  public static QGroup alias() {
    return _alias;
  }

  public PLong<QGroup> id;
  public PTimestamp<QGroup> createdAt;
  public PTimestamp<QGroup> updatedAt;
  public PBoolean<QGroup> deleted;
  public PString<QGroup> name;
  public PString<QGroup> description;
  public PString<QGroup> avatar;


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
  public static QGroup forFetchGroup() {
    return new QGroup(io.ebean.FetchGroup.queryFor(Group.class));
  }

  /** Construct using the default Database */
  public QGroup() {
    super(Group.class);
  }

  /** Construct with a given transaction */
  public QGroup(io.ebean.Transaction transaction) {
    super(Group.class, transaction);
  }

  /** Construct with a given Database */
  public QGroup(io.ebean.Database database) {
    super(Group.class, database);
  }


  /** Private constructor for Alias */
  private QGroup(boolean dummy) {
    super(dummy);
  }

  /** Private constructor for FetchGroup building */
  private QGroup(io.ebean.Query<Group> fetchGroupQuery) {
    super(fetchGroupQuery);
  }

  /** Private constructor for filterMany */
  private QGroup(io.ebean.ExpressionList<Group> filter) {
    super(filter);
  }

  /**
   * Provides static properties to use in <em> select() and fetch() </em>
   * clauses of a query. Typically referenced via static imports. 
   */
  @io.ebean.typequery.Generated("io.ebean.querybean.generator")
  public static final class Alias {
    public static PLong<QGroup> id = _alias.id;
    public static PTimestamp<QGroup> createdAt = _alias.createdAt;
    public static PTimestamp<QGroup> updatedAt = _alias.updatedAt;
    public static PBoolean<QGroup> deleted = _alias.deleted;
    public static PString<QGroup> name = _alias.name;
    public static PString<QGroup> description = _alias.description;
    public static PString<QGroup> avatar = _alias.avatar;
  }

  /**  Association query bean */
  @io.ebean.typequery.Generated("io.ebean.querybean.generator")
  @io.ebean.typequery.TypeQueryBean("v1")
  public static final class Assoc<R> extends io.ebean.typequery.TQAssocBean<Group,R,QGroup> {
    public PLong<R> id;
    public PTimestamp<R> createdAt;
    public PTimestamp<R> updatedAt;
    public PBoolean<R> deleted;
    public PString<R> name;
    public PString<R> description;
    public PString<R> avatar;

    public Assoc(String name, R root) {
      super(name, root);
    }

    public Assoc(String name, R root, String prefix) {
      super(name, root, prefix);
    }

    public final R filterMany(java.util.function.Consumer<QGroup> apply) {
      final io.ebean.ExpressionList list = io.ebean.Expr.factory().expressionList();
      final var qb = new QGroup(list);
      apply.accept(qb);
      expr().filterMany(_name).addAll(list);
      return _root;
    }
  }
}
