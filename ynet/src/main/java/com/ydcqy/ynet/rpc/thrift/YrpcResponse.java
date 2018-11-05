/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.ydcqy.ynet.rpc.thrift;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2018-11-05")
public class YrpcResponse implements org.apache.thrift.TBase<YrpcResponse, YrpcResponse._Fields>, java.io.Serializable, Cloneable, Comparable<YrpcResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("YrpcResponse");

  private static final org.apache.thrift.protocol.TField REQUEST_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("request_id", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField RESULT_FIELD_DESC = new org.apache.thrift.protocol.TField("result", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField ERR_MSG_FIELD_DESC = new org.apache.thrift.protocol.TField("err_msg", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new YrpcResponseStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new YrpcResponseTupleSchemeFactory();

  public java.lang.String request_id; // required
  public java.nio.ByteBuffer result; // required
  public java.lang.String err_msg; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    REQUEST_ID((short)1, "request_id"),
    RESULT((short)2, "result"),
    ERR_MSG((short)3, "err_msg");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // REQUEST_ID
          return REQUEST_ID;
        case 2: // RESULT
          return RESULT;
        case 3: // ERR_MSG
          return ERR_MSG;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.REQUEST_ID, new org.apache.thrift.meta_data.FieldMetaData("request_id", org.apache.thrift.TFieldRequirementType.DEFAULT,
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.RESULT, new org.apache.thrift.meta_data.FieldMetaData("result", org.apache.thrift.TFieldRequirementType.DEFAULT,
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    tmpMap.put(_Fields.ERR_MSG, new org.apache.thrift.meta_data.FieldMetaData("err_msg", org.apache.thrift.TFieldRequirementType.DEFAULT,
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(YrpcResponse.class, metaDataMap);
  }

  public YrpcResponse() {
  }

  public YrpcResponse(
          java.lang.String request_id,
          java.nio.ByteBuffer result,
          java.lang.String err_msg)
  {
    this();
    this.request_id = request_id;
    this.result = org.apache.thrift.TBaseHelper.copyBinary(result);
    this.err_msg = err_msg;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public YrpcResponse(YrpcResponse other) {
    if (other.isSetRequest_id()) {
      this.request_id = other.request_id;
    }
    if (other.isSetResult()) {
      this.result = org.apache.thrift.TBaseHelper.copyBinary(other.result);
    }
    if (other.isSetErr_msg()) {
      this.err_msg = other.err_msg;
    }
  }

  public YrpcResponse deepCopy() {
    return new YrpcResponse(this);
  }

  @Override
  public void clear() {
    this.request_id = null;
    this.result = null;
    this.err_msg = null;
  }

  public java.lang.String getRequest_id() {
    return this.request_id;
  }

  public YrpcResponse setRequest_id(java.lang.String request_id) {
    this.request_id = request_id;
    return this;
  }

  public void unsetRequest_id() {
    this.request_id = null;
  }

  /** Returns true if field request_id is set (has been assigned a value) and false otherwise */
  public boolean isSetRequest_id() {
    return this.request_id != null;
  }

  public void setRequest_idIsSet(boolean value) {
    if (!value) {
      this.request_id = null;
    }
  }

  public byte[] getResult() {
    setResult(org.apache.thrift.TBaseHelper.rightSize(result));
    return result == null ? null : result.array();
  }

  public java.nio.ByteBuffer bufferForResult() {
    return org.apache.thrift.TBaseHelper.copyBinary(result);
  }

  public YrpcResponse setResult(byte[] result) {
    this.result = result == null ? (java.nio.ByteBuffer)null : java.nio.ByteBuffer.wrap(result.clone());
    return this;
  }

  public YrpcResponse setResult(java.nio.ByteBuffer result) {
    this.result = org.apache.thrift.TBaseHelper.copyBinary(result);
    return this;
  }

  public void unsetResult() {
    this.result = null;
  }

  /** Returns true if field result is set (has been assigned a value) and false otherwise */
  public boolean isSetResult() {
    return this.result != null;
  }

  public void setResultIsSet(boolean value) {
    if (!value) {
      this.result = null;
    }
  }

  public java.lang.String getErr_msg() {
    return this.err_msg;
  }

  public YrpcResponse setErr_msg(java.lang.String err_msg) {
    this.err_msg = err_msg;
    return this;
  }

  public void unsetErr_msg() {
    this.err_msg = null;
  }

  /** Returns true if field err_msg is set (has been assigned a value) and false otherwise */
  public boolean isSetErr_msg() {
    return this.err_msg != null;
  }

  public void setErr_msgIsSet(boolean value) {
    if (!value) {
      this.err_msg = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
      case REQUEST_ID:
        if (value == null) {
          unsetRequest_id();
        } else {
          setRequest_id((java.lang.String)value);
        }
        break;

      case RESULT:
        if (value == null) {
          unsetResult();
        } else {
          if (value instanceof byte[]) {
            setResult((byte[])value);
          } else {
            setResult((java.nio.ByteBuffer)value);
          }
        }
        break;

      case ERR_MSG:
        if (value == null) {
          unsetErr_msg();
        } else {
          setErr_msg((java.lang.String)value);
        }
        break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
      case REQUEST_ID:
        return getRequest_id();

      case RESULT:
        return getResult();

      case ERR_MSG:
        return getErr_msg();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
      case REQUEST_ID:
        return isSetRequest_id();
      case RESULT:
        return isSetResult();
      case ERR_MSG:
        return isSetErr_msg();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof YrpcResponse)
      return this.equals((YrpcResponse)that);
    return false;
  }

  public boolean equals(YrpcResponse that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_request_id = true && this.isSetRequest_id();
    boolean that_present_request_id = true && that.isSetRequest_id();
    if (this_present_request_id || that_present_request_id) {
      if (!(this_present_request_id && that_present_request_id))
        return false;
      if (!this.request_id.equals(that.request_id))
        return false;
    }

    boolean this_present_result = true && this.isSetResult();
    boolean that_present_result = true && that.isSetResult();
    if (this_present_result || that_present_result) {
      if (!(this_present_result && that_present_result))
        return false;
      if (!this.result.equals(that.result))
        return false;
    }

    boolean this_present_err_msg = true && this.isSetErr_msg();
    boolean that_present_err_msg = true && that.isSetErr_msg();
    if (this_present_err_msg || that_present_err_msg) {
      if (!(this_present_err_msg && that_present_err_msg))
        return false;
      if (!this.err_msg.equals(that.err_msg))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetRequest_id()) ? 131071 : 524287);
    if (isSetRequest_id())
      hashCode = hashCode * 8191 + request_id.hashCode();

    hashCode = hashCode * 8191 + ((isSetResult()) ? 131071 : 524287);
    if (isSetResult())
      hashCode = hashCode * 8191 + result.hashCode();

    hashCode = hashCode * 8191 + ((isSetErr_msg()) ? 131071 : 524287);
    if (isSetErr_msg())
      hashCode = hashCode * 8191 + err_msg.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(YrpcResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetRequest_id()).compareTo(other.isSetRequest_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRequest_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.request_id, other.request_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetResult()).compareTo(other.isSetResult());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResult()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.result, other.result);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetErr_msg()).compareTo(other.isSetErr_msg());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetErr_msg()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.err_msg, other.err_msg);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("YrpcResponse(");
    boolean first = true;

    sb.append("request_id:");
    if (this.request_id == null) {
      sb.append("null");
    } else {
      sb.append(this.request_id);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("result:");
    if (this.result == null) {
      sb.append("null");
    } else {
      org.apache.thrift.TBaseHelper.toString(this.result, sb);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("err_msg:");
    if (this.err_msg == null) {
      sb.append("null");
    } else {
      sb.append(this.err_msg);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class YrpcResponseStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public YrpcResponseStandardScheme getScheme() {
      return new YrpcResponseStandardScheme();
    }
  }

  private static class YrpcResponseStandardScheme extends org.apache.thrift.scheme.StandardScheme<YrpcResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, YrpcResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
          break;
        }
        switch (schemeField.id) {
          case 1: // REQUEST_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.request_id = iprot.readString();
              struct.setRequest_idIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // RESULT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.result = iprot.readBinary();
              struct.setResultIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // ERR_MSG
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.err_msg = iprot.readString();
              struct.setErr_msgIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, YrpcResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.request_id != null) {
        oprot.writeFieldBegin(REQUEST_ID_FIELD_DESC);
        oprot.writeString(struct.request_id);
        oprot.writeFieldEnd();
      }
      if (struct.result != null) {
        oprot.writeFieldBegin(RESULT_FIELD_DESC);
        oprot.writeBinary(struct.result);
        oprot.writeFieldEnd();
      }
      if (struct.err_msg != null) {
        oprot.writeFieldBegin(ERR_MSG_FIELD_DESC);
        oprot.writeString(struct.err_msg);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class YrpcResponseTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public YrpcResponseTupleScheme getScheme() {
      return new YrpcResponseTupleScheme();
    }
  }

  private static class YrpcResponseTupleScheme extends org.apache.thrift.scheme.TupleScheme<YrpcResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, YrpcResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetRequest_id()) {
        optionals.set(0);
      }
      if (struct.isSetResult()) {
        optionals.set(1);
      }
      if (struct.isSetErr_msg()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetRequest_id()) {
        oprot.writeString(struct.request_id);
      }
      if (struct.isSetResult()) {
        oprot.writeBinary(struct.result);
      }
      if (struct.isSetErr_msg()) {
        oprot.writeString(struct.err_msg);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, YrpcResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.request_id = iprot.readString();
        struct.setRequest_idIsSet(true);
      }
      if (incoming.get(1)) {
        struct.result = iprot.readBinary();
        struct.setResultIsSet(true);
      }
      if (incoming.get(2)) {
        struct.err_msg = iprot.readString();
        struct.setErr_msgIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

